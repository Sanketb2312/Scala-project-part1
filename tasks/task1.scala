import scala.collection.mutable.MutableList

object Task1 {
    def createList(): MutableList[Int] = {
        val list = MutableList[Int]()
        for ( i <- 0 to 50) {
            list += i
        }
        
        list
    }

    def sumList(list: List[Int]): Int = {
        var sum = 0
        for ( n <- list ) {
            sum += n
        }

        sum
    }

    def sumListRecursive(list: List[Int]) : Int = {

        def sumIter(list: List[Int], partial: Int) : Int = list match {
            case h::t => sumIter(t, partial + h)
            case Nil => partial
        }

        sumIter(list, 0)
    }

    def fibonacci(n: BigInt) : BigInt = {

        def fibIter(n: BigInt, partial: BigInt) : BigInt = {
            if (n <= 0 ) {
                partial
            } else {
                fibIter(n - 1, partial * n)
            }
        }

        fibIter(n, 1)
    }

    // BigInt has no max size and can use as much memory as you have available
    // Int uses 32 bits
}