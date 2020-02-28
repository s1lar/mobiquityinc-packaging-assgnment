package com.mobiquityinc.packer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Packer}
 *
 * @author igor.sila (isila)
 */
public class PackerTest {

    @Test
    public void parse_valid_file() throws APIException, IOException {
        final String pack = Packer.pack("src/main/test/resources/example_input");

        final var sb = new StringBuilder();
        Files.lines(Paths.get("src/main/test/resources/example_output"))
             .forEach(line -> sb.append("\n").append(line));

        assertEquals(sb.toString(), pack);
    }

    @Test
    public void parse_invalid_file() {
        Assertions.assertThrows(APIException.class, () ->
                Packer.pack("src/main/test/resources/invalid_examples/invalid_input_1"));
    }
}
