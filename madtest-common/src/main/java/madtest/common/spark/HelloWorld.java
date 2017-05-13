package madtest.common.spark;


import spark.Spark;

/**
 * Created by qct on 2015/5/17.
 */
public class HelloWorld {

    public static void main(String[] args) {
        Spark.get("/hello", (req, res) -> "Hello World");
    }
}
