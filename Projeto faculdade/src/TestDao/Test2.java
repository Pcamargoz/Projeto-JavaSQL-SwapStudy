package TestDao;

import Modelos.DaoFactory;
import Modelos.UsuarioDao;
import program.Usuario;

import java.util.List;
import java.util.Scanner;

public class Test2 {
    static void main() {
        // aqui vamos trabalhar com o Department DAO
        System.out.println(" === Test 1: Usuario findById ====");
        UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
        Usuario dep = usuarioDao.findById(2);
        System.out.println(dep);

        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== Test 2: department findALL =====");
        List<Usuario> list = usuarioDao.findALL();
        for(Usuario obj : list){
            System.out.println(obj);
        }

        // inserindo alguem no banco de Dados
        /*System.out.println("\n=== Test 3: Department Insert =====");
        Department newDepartment = new Department(null,"Ouvidoria");
        departmentDao.insert(newDepartment);
        System.out.println("Inserted! New Id = " + newDepartment.getId());*/

        /*System.out.println("\n=== Test 4: Department Update =====");
        // vai achar alguem com o id que queremos
        dep = departmentDao.findById(1);
        dep.setName("Martha Waine");
        // e confirmando a alteração com o update
        departmentDao.update(dep);
        System.out.println("Update Completed");*/

        /*System.out.println("\n=== Test 5: Usuario Delete =====");
        System.out.println("Enter Id For Delete: ");
        int id = sc.nextInt();
        usuarioDao.deleteById(id);
        System.out.println("Delete completed");*/
    }
}
