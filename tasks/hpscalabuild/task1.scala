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

    //Sanket løsning
    def createList(): Array[Int] = {
        val list = new Array[Int](50)
        for(i <- 1 to 50) {
            list(i-1)=i
        }

        list
    }

    def sumList(list: List[Int]): Int = {
        var sum: Int = 0
        list.foreach(sum+=_)
        sum
    }

    def sumRecursion(list: List[Int]): Int = {

        if (list.length <=1) {
            return list(0)
        }
        var tmpList: (List[Int], List[Int]) = list.splitAt(1)

        return tmpList(0)(0)+sumRecursion(tmpList(1))
    }

    def fibonacci(bigInt: BigInt): BigInt = {
        if(bigInt == 0 || bigInt == 1) {
            return 1
        }

        return fibonacci(bigInt-1)+fibonacci(bigInt-2)
    }

    @main def main()= {
         println(createList().mkString(" "))
        println(sumList(List(1,2,3,4,5,6,7,8,9,10)))
        println(sumRecursion(List(1,2,3,4,5)))
        println(fibonacci(5))
    }

}
