package bussiness
import classes.*
import repository.taskRepository

import java.lang.Exception

class TaskBussiness(){

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

    private fun validateTitulo(titulo: String): Boolean{

        var estado = true

        if(titulo=="")
            estado = false

        return estado
    }

    fun save(titulo: String, data: String, descricao: String){

        if(validateTitulo(titulo) && validateData(data)!=null) { // dados válidos

            var data = validateData(data)

            var task = data?.let { Task(titulo, it, descricao) }

            if (task != null) {
                taskRepository.save(task)
            }

        }else { // dados invalidos

            if(!validateTitulo(titulo))
                throw Exception("Título não pode ser vazio")
            if(validateData(data)==null)
                throw Exception("Data inválida")
        }

    }

    fun delete(titulo: String){

        taskRepository.delete(titulo)

    }

    fun getList(): ArrayList<Task> {

        return taskRepository.getList()

    }
}
