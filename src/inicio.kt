import java.lang.Exception

data class Data(var dia: Int, var mes: Int, var ano: Int)
data class Task(var titulo: String, var data: Data, var descricao: String)

fun validateData(dataString: String): Data?{

     var dataInt: Int
     var data: Data

     try{
          dataInt = dataString.toInt()
          data = Data(dataInt/10000, (dataInt%10000 - dataInt/10000)/100, dataInt%100 )

          with(data){

               if(mes>12 || mes<1 || dia<1 || dia > 31)
                    return null

          }
          return data
     } catch (e: Exception){
          return null
     }
}

fun validateTitulo(titulo: String): Boolean{

     var estado = true

     if(titulo=="")
          estado = false

      return estado
}

fun save(titulo: String, data: String, descricao: String){

     if(validateTitulo(titulo) && validateData(data)!=null) { // dados válidos



     }else { // dados invalidos

     }

}

fun main() {

     MainForm()

}

