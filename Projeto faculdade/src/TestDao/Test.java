package TestDao;

import Modelos.ContratoDao;
import Modelos.DaoFactory;
import Modelos.UsuarioDao;
import program.PuxarContratos;
import program.Usuario;

import java.util.List;
import java.util.Scanner;

public class Test {
    static void main() {
        System.out.println(" === Test 1: Contrato findById ====");
        ContratoDao contratoDao = DaoFactory.createContratoDao();
        PuxarContratos c1 = contratoDao.findById(2);
        System.out.println(c1);

        /*Scanner sc = new Scanner(System.in);
        System.out.println("\n=== Test 2: contrato findALL =====");
        // guardando dentro de uma lista aqui a lista de la para que a gente possa imprimir no for
        List<PuxarContratos> list = contratoDao.findALL();
        for(PuxarContratos obj : list){
            System.out.println(obj);
        }*/

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

        /*System.out.println("\n=== Test 5: Department Delete =====");
        System.out.println("Enter Id For Delete: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Delete completed");*/
    }
}
