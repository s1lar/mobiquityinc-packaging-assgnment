package com.mobiquityinc.fileparser;

import com.mobiquity.exception.APIException;
import com.mobiquity.fileparser.FileParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link FileParser}
 *
 * @author igor.sila (isila)
 * @since 0.16.0
 */
public class FileParserTest {


    private FileParser parser;

    @BeforeEach
    void setupThis() {
        this.parser = new FileParser();
    }

    @Test
    public void parse_valid_file() throws APIException {
        this.parser.parseFile("src/main/test/resources/example_input");
    }

    @Test
    public void parse_invalid_file__no_file() {
        Assertions.assertThrows(APIException.class, () -> this.parser
                .parseFile("src/main/test/resources/invalid_examples/1.txt"));
    }

    @Test
    public void parse_invalid_file__too_big_package_weight() {
        Assertions.assertThrows(APIException.class, () -> this.parser
                .parseFile("src/main/test/resources/invalid_examples/invalid_input_1.txt"));
    }

    @Test
    public void parse_invalid_file__negative_index() {
        Assertions.assertThrows(APIException.class, () -> this.parser
                .parseFile("src/main/test/resources/invalid_examples/invalid_input_2.txt"));
    }
    @Test
    public void parse_invalid_file__too_big_item_price() {
        Assertions.assertThrows(APIException.class, () -> this.parser
                .parseFile("src/main/test/resources/invalid_examples/invalid_input_3.txt"));
    }
    @Test
    public void parse_invalid_file__no_items() {
        Assertions.assertThrows(APIException.class, () -> this.parser
                .parseFile("src/main/test/resources/invalid_examples/invalid_input_4.txt"));
    }
    @Test
    public void parse_invalid_file__no_package_weight() {
        Assertions.assertThrows(APIException.class, () -> this.parser
                .parseFile("src/main/test/resources/invalid_examples/invalid_input_5.txt"));
    }
    @Test
    public void parse_invalid_file__invalid_data() {
        Assertions.assertThrows(APIException.class, () -> this.parser
                .parseFile("src/main/test/resources/invalid_examples/invalid_input_6.txt"));
    }
}
