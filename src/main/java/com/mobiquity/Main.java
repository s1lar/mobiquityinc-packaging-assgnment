package com.mobiquity;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;

/**
 * Application for local testing
 *
 * @author igor.sila (isila)
 */
public class Main {

    public static void main(String[] args) throws APIException {

        if(args.length == 0) {
            System.out.println(Packer.pack("src/main/test/resources/example_input"));
        } else {
            System.out.println(Packer.pack(args[0]));
        }
    }
}
