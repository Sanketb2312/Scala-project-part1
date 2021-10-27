import Task1.{createList, sumList, sumListRecursive, fibonacci}

object Main extends App {
    // val list = createList();
    // list.foreach((i) => println(i));

    val list = List(1, 2, 3, 4);
    
    println(sumList(list));
    println(sumListRecursive(list));

    println(fibonacci(5))
}