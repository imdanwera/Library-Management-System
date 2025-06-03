package com.ascender.library.integration;

import com.intuit.karate.junit5.Karate;

class BookApiTest {

    @Karate.Test
    Karate testBookApi() {
        return Karate.run("book-api").relativeTo(getClass());
    }
}
