package spark;


import static spark.Spark.get;

/**
 * Created by qct on 2015/5/17.
 */
public class HelloWorld {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }
}
