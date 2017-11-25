package shop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import shop.ShopException;
import shop.controller.IController;

/**
 * Created by Sebi on 25-Nov-17.
 */
@RestController
public class ShopController {
    @Autowired
    private IController controller;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<?> getProducts(){
        try {
            return new ResponseEntity<>(controller.getProducts(), HttpStatus.OK);
        } catch (ShopException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
