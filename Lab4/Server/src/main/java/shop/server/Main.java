package shop.server;

import shop.consistenceChecker.ConsistenceChecker;
import shop.controller.Controller;
import shop.repository.FileRepo;
import shop.repository.Repository;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class Main {
    public static void main(String[] args) throws ServerException {
        Server server = new Server(3001);

        FileRepo fileRepo = new FileRepo();
        Repository repository = new Repository();
        repository.setFileRepo(fileRepo);
        Controller controller = new Controller(repository);
        server.setController(controller);

        Thread t = new Thread(new ConsistenceChecker(controller));
        t.start();

        server.start();
    }
}
