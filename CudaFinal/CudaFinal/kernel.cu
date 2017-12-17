#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <iostream>
#include <math.h>
#include "cuda_runtime.h"
#include "device_launch_parameters.h"

using namespace cv;
using namespace std;


#define THREADS_PER_BLOCK 1024

// serial
Mat rotateImage(Mat image, double theta) {
	float rads = (theta * 3.14159265) / 180.0;

	int r0 = image.rows / 2;
	int c0 = image.cols / 2;

	Mat newImage(image.rows, image.cols, CV_8UC3, Scalar(0, 0, 0));
	for (int r = 0; r < image.rows; r++)
	{
		for (int c = 0; c < image.cols; c++)
		{
			// calculate new coordinates
			int r1 = (int)(r0 + ((r - r0) * cos(rads)) - ((c - c0) * sin(rads)));
			int c1 = (int)(c0 + ((r - r0) * sin(rads)) + ((c - c0) * cos(rads)));

			// set pixel
			if (r1 >= 0 && r1 < image.rows && c1 >= 0 && c1 < image.cols) {
				newImage.at<Vec3b>(r1, c1) = image.at<Vec3b>(r, c);
			}
		}
	}

	return newImage;
}



// parallel
__global__ void rotate(int* pixelPositions, int* rows, int* cols, double* sinOfRads, double* cosOfRads) {
	// get position
	int index = threadIdx.x + blockIdx.x * blockDim.x;
	int r = (int)(index / *cols);
	int c = index % *cols;

	int r0 = *rows / 2;
	int c0 = *cols / 2;

	// calculate new coordinates
	int r1 = (int)(r0 + ((r - r0) * *cosOfRads) - ((c - c0) * *sinOfRads));
	int c1 = (int)(c0 + ((r - r0) * *sinOfRads) + ((c - c0) * *cosOfRads));

	// set pixel
	if (r1 >= 0 && r1 < *rows && c1 >= 0 && c1 < *cols) {
		pixelPositions[index] = r1 * (*cols) + c1;
	}

}

Mat parallelOperations(Mat image, double theta) {
	// init values
	float rads = (theta * 3.14159265) / 180.0;
	double sinOfRads = sin(rads);
	double cosOfRads = cos(rads);
	int rows = image.rows;
	int cols = image.cols;
	Mat newImage(image.rows, image.cols, CV_8UC3, Scalar(0, 0, 0));

	int* pixelPositions = (int*)malloc(image.rows * image.cols * sizeof(int));

	int *d_pixelPositions; double *d_sinOfRads, *d_cosOfRads; int *d_rows, *d_cols;  // device copies

	// Alloc space for device copies
	cudaError_t err;
	err = cudaMalloc((void **)&d_pixelPositions, image.rows * image.cols * sizeof(int));
	if (err != cudaSuccess) {
		cout << "alloc error";
	}
	err = cudaMalloc((void **)&d_sinOfRads, sizeof(double));
	if (err != cudaSuccess) {
		cout << "alloc error";
	}
	err = cudaMalloc((void **)&d_cosOfRads, sizeof(double));
	if (err != cudaSuccess) {
		cout << "alloc error";
	}
	err = cudaMalloc((void **)&d_rows, sizeof(int));
	if (err != cudaSuccess) {
		cout << "alloc error";
	}
	err = cudaMalloc((void **)&d_cols, sizeof(int));
	if (err != cudaSuccess) {
		cout << "alloc error";
	}

	// Copy inputs to device
	err = cudaMemcpy(d_pixelPositions, &image, sizeof(int), cudaMemcpyHostToDevice);
	if (err != cudaSuccess) {
		cout << "alloc error";
	}
	err = cudaMemcpy(d_sinOfRads, &sinOfRads, sizeof(double), cudaMemcpyHostToDevice);
	if (err != cudaSuccess) {
		cout << "alloc error";
	}
	err = cudaMemcpy(d_cosOfRads, &cosOfRads, sizeof(double), cudaMemcpyHostToDevice);
	if (err != cudaSuccess) {
		cout << "alloc error";
	}
	err = cudaMemcpy(d_rows, &rows, sizeof(int), cudaMemcpyHostToDevice);
	if (err != cudaSuccess) {
		cout << "alloc error";
	}
	err = cudaMemcpy(d_cols, &cols, sizeof(int), cudaMemcpyHostToDevice);
	if (err != cudaSuccess) {
		cout << "alloc error";
	}
	
	// Launch rotate() kernel on GPU 
	rotate <<<(image.rows * image.cols + THREADS_PER_BLOCK - 1) / THREADS_PER_BLOCK, THREADS_PER_BLOCK >>>(d_pixelPositions, d_rows, d_cols, d_sinOfRads, d_cosOfRads);
	err = cudaThreadSynchronize();
	if (err != cudaSuccess) {
		cout << "sync error";
	}

	// Copy result back to host
	err = cudaMemcpy(pixelPositions, d_pixelPositions, image.rows * image.cols * sizeof(int), cudaMemcpyDeviceToHost);
	if (err != cudaSuccess) {
		cout << "alloc error";
	}

	// Cleanup
	cudaFree(d_pixelPositions); cudaFree(d_sinOfRads); cudaFree(d_cosOfRads); cudaFree(d_rows); cudaFree(d_cols);

	for (int i = 0; i < image.rows * image.cols; i++) {
		int rowFirstMat = i / image.cols;
		int colFirstMat = i % image.cols;

		int rowNewMat = pixelPositions[i] / image.cols;
		int colNewMat = pixelPositions[i] % image.cols;

		//cout << rowFirstMat << " " << colFirstMat << endl;
		//cout << rowNewMat << " " << colNewMat << endl;

		if (rowNewMat >= 0 && rowNewMat < image.rows && colNewMat >= 0 && colNewMat < image.cols) {
			newImage.at<Vec3b>(rowNewMat, colNewMat) = image.at<Vec3b>(rowFirstMat, colFirstMat);
		}
	}

	return newImage;
}


void processImg() {
	Mat image;
	image = imread("mitzi.bmp", CV_LOAD_IMAGE_COLOR);   // Read the file

	if (!image.data)                              // Check for invalid input
	{
		cout << "Could not open or find the image" << std::endl;
		return;
	}

	cout << "Input theta : ";
	double theta;
	cin >> theta;

	Mat rotated = parallelOperations(image, theta);

	namedWindow("Display window", WINDOW_AUTOSIZE);// Create a window for display.
	imshow("Display window", rotated);                   // Show our image inside it.
	waitKey(0);                                          // Wait for a keystroke in the window
}


int main(void) {
	processImg();
}