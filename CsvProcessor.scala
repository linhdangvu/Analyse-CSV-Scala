import scala.io.StdIn._;
import scala.collection.mutable.HashMap
import scala.collection.mutable.Set

object CsvProcessor {

		var content = Array.ofDim[Any](100,50000)
		var map: HashMap[Int, String] = HashMap()
		var num : Int = 0
		var cols : Array[Any] = Array()
		
   def main(args: Array[String]) {	
		
		loadFile
		printMenu
		Iterator.continually(io.StdIn.readLine).takeWhile(_ != "x").foreach{   
           case "1" => loadFile; printMenu;
		   case "2" => println("Total Number of Columns : " + map.size); printMenu;
		   case "3" => println("Total Number of Data : " + num); printMenu;
           case "4" => {  
							println("Column ( 0 to " +(map.size-1)+") ? ")
							processCol((io.StdIn.readLine).toInt,num);
							printMenu;
						}
		   case "5" => printAll;  printMenu;
		   case "6" => {	println("Column ( 0 to " +(map.size-1)+") ? ")
							fill((io.StdIn.readLine).toInt,num);
							printMenu;
						}
           case _      => printMenu
        }
		
		
		/*process(0,num)
		process(1,num)
		printAll*/
   }
   
   def printMenu : Unit = {
		   println()
		   println("----------------MENU----------------")
		   println("1. Load File")
		   println("2. Number of Columns")
		   println("3. Total Number of Records")
		   println("4. Column Summary")
		   println("5. All Columns Summary")
		   println("6. Fill Missing values")
		   println("x. Exit")
		   println("************************************")
		   println()
		   print("Option : ")
   }
   
   def loadFile : Unit = {
		val file 	= readLine("Full path of the file : ");
		val bufferedSource = io.Source.fromFile(file)
		println();
		num 	= 0
		content = Array.ofDim[Any](100,50000)
		map 	= HashMap()
		cols 	= Array()
		
		for (line <- bufferedSource.getLines) {
			if( num == 0){
				cols = line.split(";").map(_.trim) //soit ; soit ,
				for (i <- 0 until cols.length){
					map(i) = cols(i).toString
				}
			}else{
				cols = line.split(";").map(_.trim) //soit ; soit ,
				for (i <- 0 until cols.length){
					content(i)(num-1) = cols(i)
				}
			}
			num+=1
		}
		bufferedSource.close
   }
 
	
	def process( col : Int, max : Int ) : Unit = {
	
		var arr: Array[Any] = content(col)
		var typ : String = ""
		var n_count : Int = 0;
		var str : Boolean = false
		var unq : Set[String] = Set()
		
		print("**********")
		print(" Summary of Column " + map(col) + " at index " + col+ " ")
		println("**********")
		println("")
		
		
		for (i <- 0 until max-1){
			if(arr(i) == ""){
				n_count +=1
			}else if(!str){
				try{
					unq.add(arr(i).toString);
					var v = arr(i).toString.toDouble
					typ = "Double"
				}catch{
					case x: NumberFormatException =>
					{
						typ = "String"
						str = true
					}	
				}
			}else{
				unq.add(arr(i).toString);
			}
		}
		
		println("Column Type  : " + typ)
		println("Null count   : " + n_count)
		println("Unique count : " + unq.size)
		println("")
		println("********** End of Summary ********** ")
		println("")	
	}

	def processCol( col : Int, max : Int ) : Unit = {
	
		var arr: Array[Any] = content(col)
		var typ : String = ""
		var n_count : Int = 0;
		var str : Boolean = false
		var unq : Set[String] = Set()
		
		print("**********")
		print(" Summary of Column " + map(col) + " at index " + col+ " ")
		println("**********")
		println("")
		
		
		for (i <- 0 until max-1){
			if(arr(i) == ""){
				n_count +=1
			}else if(!str){
				try{
					unq.add(arr(i).toString);
					var v = arr(i).toString.toDouble
					typ = "Double"
				}catch{
					case x: NumberFormatException =>
					{
						typ = "String"
						str = true
					}	
				}
			}else{
				unq.add(arr(i).toString);
			}
		}
		
		println("Column Type  : " + typ)
		println("Null count   : " + n_count)
		println("Unique count : " + unq.size)
		println("")		
		if(typ == "Double"){
			numSummary(col,max)
		}else{		
			nomSummary(col,max)
		}
		println("")
		println("********** End of Summary ********** ")
		println("")	
	}
	
	def printAll : Unit = {
		for (i <- 0 until map.size){
			process(i,num);
		}
	}
	
	def numFill( col : Int, max : Int ) : Int = {
		var arr: Array[Any] = content(col)
		var sum : Float = 0
		var count : Int = 0
		
		for (i <- 0 until max-1){
			if(arr(i) == ""){
				sum +=0
				count +=1
			}else if(arr(i) != null){
					sum += arr(i).toString.toFloat
					count +=1
			}
		}
		return Math.round(sum/count)
	}

	def numSummary( col : Int, max : Int ) : Int = {
		var arr: Array[Any] = content(col)
		var sum : Float 	= 0
		var count : Int 	= 0
		var maxval : Double = -100000000;
		var minval : Double =  100000000;
		
		for (i <- 0 until max-1){
			if(arr(i) == ""){
				sum +=0
				count +=1
			}else if(arr(i) != null){
					sum += arr(i).toString.toFloat
					count +=1
					
					if(maxval < arr(i).toString.toFloat){
						maxval = arr(i).toString.toFloat;
					}
					
					if(minval > arr(i).toString.toFloat){
						minval = arr(i).toString.toFloat;
					}
			}
		}
		
		println("Total   : "+ sum);
		println("Average : "+ (sum/count));
		println("Min	 : "+ minval);
		println("Max	 : "+ maxval);
		
		
		return Math.round(sum/count)
	}
	
	def nomSummary( col : Int, max : Int ) : Unit = {
		var arr: Array[Any] = content(col)
		var tempMap: HashMap[String,Double] = HashMap()
		
		
		var count : Int = 0
		
		for (i <- 0 until max-1){
			if(!tempMap.contains(arr(i).toString)){
				tempMap(arr(i).toString) = 1
			}else{
				tempMap(arr(i).toString) = tempMap(arr(i).toString)+1
			}
		}
		for ((k,v) <- tempMap) printf("%-25s : %.5f\n",k, (v/(max.toDouble)))		
	}
	
	def nomFill( col : Int, max : Int ) : String = {
		var arr: Array[Any] = content(col)
		var tempMap: HashMap[String,Double] = HashMap()
		
		var tempMax : Double = -1.0
		var tempVal : String = "";
		
		var count : Int = 0
		
		for (i <- 0 until max-1){
			if(!tempMap.contains(arr(i).toString)){
				tempMap(arr(i).toString) = 1
			}else{
				tempMap(arr(i).toString) = tempMap(arr(i).toString)+1
			}
		}
		
		for ((k,v) <- tempMap) if(v > tempMax) {tempVal = k; tempMax = v;}
		
		return tempVal		
	}
	
	def fillNumCol( col : Int, max : Int ) : Unit = {
	
		var arr: Array[Any] = content(col)
		var typ : String = ""
		var n_count : Int = 0;
		var fill : Double = numFill(col,max);
		
		for (i <- 0 until max-1){
			if(arr(i) == ""){
				arr(i) = fill
			}
		}
	}
	
		def fillNomCol( col : Int, max : Int ) : Unit = {
	
		var arr: Array[Any] = content(col)
		var typ : String = ""
		var n_count : Int = 0;
		var fill : String = nomFill(col,max);
		
		for (i <- 0 until max-1){
			if(arr(i) == ""){
				arr(i) = fill
			}
		}
	}
	
	def fill( col : Int, max : Int ) : Unit = {
	
		var arr: Array[Any] = content(col)
		var typ : String = ""
		var n_count : Int = 0;
		var str : Boolean = false
		
		for (i <- 0 until max-1){
			if(arr(i) == ""){
				n_count +=1
			}else if(!str){
				try{
					var v = arr(i).toString.toDouble
					typ = "Double"
				}catch{
					case x: NumberFormatException =>
					{
						typ = "String"
						str = true
					}	
				}
			}
		}
		
		if(n_count > 0){
			if(typ == "Double"){
				fillNumCol(col,max)
			}else{		
				fillNomCol(col,max)
			}
			println("Replaced " + n_count+" null values")
		}else{
			println("No Null Values")
		}
	}
}