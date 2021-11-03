/*object Task2 extends App {
  
  // a)
  def createThread(body: => Unit): Thread = {
    val thread = new Thread {
      override def run() = body
    }

    thread
  }

  // b, c) 
  private var counter: Int = 0
  def increaseCounter(): Unit = this.synchronized {
    counter += 1
  }

  def printCounter(): Unit = {
    println(counter)
  }

  val t1 = createThread(increaseCounter)
  val t2 = createThread(increaseCounter)
  val t3 = createThread(printCounter)

  t1.start()
  t2.start()
  t3.start()

  // d)
  lazy val a: Int = {
    def useA(): Unit = {
      println(a)
    }

    val locked = createThread(useA)
    locked.start()
    locked.join()

    10
  }

  println(a + 10)
}*/

// Sanket
import java.util.concurrent.atomic.AtomicReference
//Task 2a

def createFunctionThread(function: ()=> Unit ): Thread = {
  new Thread( new Runnable {
    def run(): Unit = {
      function()
    }
  })

}

//Task 2b
private var counter: Int = 0
//Task 2c
var safe_counter: AtomicReference[Int] = new AtomicReference[Int](0)
def increaseCounter(): Unit = {
  counter += 1 }

def printCurrentVariable() = {
  println(counter)
}

//Task 2d

lazy val lock1 = new Object()
lazy val lock2 = new Object()

class Thread1 extends Thread {
  override def run() = {
    lock1.synchronized {
      println("Thread 1 is holding lock1")
      try {
        Thread.sleep(10)
      } catch  {
        case e: InterruptedException => println("Thread 1 waiting for lock 2")
      }

      lock2.synchronized {
        println("Lock 1 is holding lock 1 and lock 2")
      }
    }
  }

}

class Thread2 extends Thread {
  override def run() = {
    lock2.synchronized {
      println("Thread 2 is holding lock2")
      try {
        Thread.sleep(10)
      } catch {
        case e: InterruptedException =>
          println("Thread 2 waiting for lock 2")
      }

      lock1.synchronized {
        println("Lock 2 is holding lock 1 and lock 2")
      }
    }
  }

}



@main def main() = {
  /*val thread1 = createFunctionThread(increaseCounter)
  val thread2 = createFunctionThread(increaseCounter)
  val thread3 = createFunctionThread(printCurrentVariable)
  thread1.start()
  thread2.start()
  thread3.start()*/

  val thread1 = new Thread1()
  val thread2 = new Thread2()
  thread1.start()
  thread2.start()


}
