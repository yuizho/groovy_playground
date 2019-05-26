import spock.lang.*

class MyFirstSpock extends Specification {

    // テストメソッド（フィーチャーメソッド）
    def "こんにちはSpock!"() {
        expect:
        1 > 0
    }

    def "maximum of two numbers"(int a, int b, int c) {
       expect:
       Math.max(a, b) == c

       where:
       a | b | c
       1 | 3 | 3
    }
}