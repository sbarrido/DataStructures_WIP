import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HTCTest {
    // TODO: accuracy tests
    HashTableWithChaining chain;

    @BeforeEach
    void initTable() {
        chain = new HashTableWithChaining<>();
    }

    @Test
    void initTest() {
        assertEquals(0, chain.size());
        assertEquals(11, chain.capacity());
    }
}
