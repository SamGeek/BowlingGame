class BowlingEngine{

    data class FrameResult(var values : MutableList<String> = mutableListOf())

    companion object {


        var result : MutableList<Int> = mutableListOf()

     fun computeGame(data : MutableList<FrameResult>): String{
         var resultString=""
         result= mutableListOf()

         //computation here
         data.forEachIndexed { index, _ ->
             result.add(calculate(index, data))
             println(result[index])
             resultString+="| ${result[index]}"
         }

         return resultString
     }

     fun calculate(index : Int, data : MutableList<FrameResult> ) : Int{

         var sum = 0
         var frameResult=0

         if(index!=0){
            sum =  result[index-1]
         }

         //we have to check next item

         if(data[index].values.size==2 && data[index].values[1].equals("SPARE")){//SPARE CASE

             if( data[index+1].values[0].equals("STRIKE") ){//check if STRIKE
                 //add 10 to the sum
                 frameResult = sum  + 20
             }else{
                 frameResult = sum  + 10 + data[index+1].values[0].toInt()
             }


         }else if(data[index].values.size==1){//STRIKE Case

             if( data[index+1].values[0].equals("STRIKE")  ){//check if STRIKE
                 //add 10 to the sum

                 if (index+1== data.size-1){
                     if(data[index+1].values[1].equals("STRIKE")){
                         frameResult = sum  + 30
                     }else{
                         frameResult = sum+ 20+ data[index+1].values[1].toInt()
                     }

                     return frameResult
                 }

                 if(data[index+2].values[0].equals("STRIKE")){
                     frameResult = sum  + 30
                 }else{
                     frameResult = sum  + 20 +data[index+2].values[0].toInt()//change to test
                 }

             }else {
                 if(data[index+1].values.size==2){
                     if (data[index+1].values[1].equals("SPARE")){
                         frameResult = sum  + 20
                     }else{
                         frameResult = sum + 10 + data[index+1].values[0].toInt()+ data[index+1].values[1].toInt()
                     }
                 }else{//we are at the end with 3 values
                     frameResult = sum + 20
                 }
             }

         }else if(data[index].values.size==3){

             if(index==data.size-1){

                 if (data[index].values[0].equals("STRIKE")&&data[index].values[1].equals("STRIKE")&&data[index].values[2].equals("STRIKE")){
                    frameResult = sum + 30
                     return frameResult
                 }

                 if (data[index].values[1].equals("SPARE")){
                    frameResult = sum + 10

                     if (data[index].values[2].equals("STRIKE")){
                         frameResult+= 10
                     }else{
                         frameResult+= data[index].values[2].toInt()
                     }

                     return frameResult
                 }

                 if (data[index].values[0].equals("STRIKE")){
                     frameResult = sum

                     if(data[index].values[2].equals("SPARE")){
                         frameResult+= 20
                         return frameResult
                     }

                     if (data[index].values[2].equals("STRIKE")&& !data[index].values[1].equals("STRIKE")){
                         frameResult+= 10 + data[index].values[1].toInt()
                         return frameResult
                     }
                     if (data[index].values[1].equals("STRIKE")&& !data[index].values[2].equals("STRIKE")){
                         frameResult+= 10 + data[index].values[2].toInt()
                         return frameResult
                     }
                     if (!data[index].values[1].equals("STRIKE")&& !data[index].values[2].equals("STRIKE")){
                         frameResult+= 10 + data[index].values[1].toInt()+ data[index].values[2].toInt()
                         return frameResult
                     }
                 }


             }


         }else{
             frameResult = sum  + data[index].values[0].toInt() + data[index].values[1].toInt()
         }

         return frameResult
     }

     fun getExampeleData () :MutableList<FrameResult>{

         val exempleData : MutableList<FrameResult> = mutableListOf()


         //test1

         exempleData.add(FrameResult(mutableListOf("1","4")))
         exempleData.add(FrameResult(mutableListOf("4","5")))
         exempleData.add(FrameResult(mutableListOf("6", "SPARE")))
         exempleData.add(FrameResult(mutableListOf("5", "SPARE")))
         exempleData.add(FrameResult(mutableListOf("STRIKE")))
         exempleData.add(FrameResult(mutableListOf("0","1")))
         exempleData.add(FrameResult(mutableListOf("7","SPARE")))
         exempleData.add(FrameResult(mutableListOf("6","SPARE")))
         exempleData.add(FrameResult(mutableListOf("STRIKE")))
         exempleData.add(FrameResult(mutableListOf("2","SPARE","6")))



    //             test2
        /* exempleData.add(FrameResult(mutableListOf("STRIKE")))
         exempleData.add(FrameResult(mutableListOf("7","SPARE")))
         exempleData.add(FrameResult(mutableListOf("9", "0")))
         exempleData.add(FrameResult(mutableListOf("STRIKE")))
         exempleData.add(FrameResult(mutableListOf("0","8")))
         exempleData.add(FrameResult(mutableListOf("8","SPARE")))
         exempleData.add(FrameResult(mutableListOf("0","6")))
         exempleData.add(FrameResult(mutableListOf("STRIKE")))
         exempleData.add(FrameResult(mutableListOf("STRIKE")))
         exempleData.add(FrameResult(mutableListOf("STRIKE","8","1")))*/

    //             test3
         /*exempleData.add(FrameResult(mutableListOf("STRIKE")))
         exempleData.add(FrameResult(mutableListOf("STRIKE")))
         exempleData.add(FrameResult(mutableListOf("STRIKE")))
         exempleData.add(FrameResult(mutableListOf("STRIKE")))
         exempleData.add(FrameResult(mutableListOf("STRIKE")))
         exempleData.add(FrameResult(mutableListOf("STRIKE")))
         exempleData.add(FrameResult(mutableListOf("STRIKE")))
         exempleData.add(FrameResult(mutableListOf("STRIKE")))
         exempleData.add(FrameResult(mutableListOf("STRIKE")))
         exempleData.add(FrameResult(mutableListOf("STRIKE","STRIKE","STRIKE")))*/


         return exempleData
     }


    }


 }

