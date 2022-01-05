package repository
import classes.*

class taskRepository {

    // chamar sempre o mesmo objeto da classe
    companion object{

        var taskList = arrayListOf<Task>()

        fun save(task: Task){

            taskList.add(task)

        }

        fun delete(titulo: String) {

            var index: Int = -1

            for(i in taskList.withIndex()){

                if (i.value.titulo == titulo){

                    index = i.index
                    break

                }

            }

            if(index != -1)
                taskList.removeAt(index)

        }

        fun getList(): ArrayList<Task> {

            return taskList

        }


    }


}