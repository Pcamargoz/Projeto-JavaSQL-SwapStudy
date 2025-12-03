package Modelos;

import ModelDaoImp.ContratoDaoJDBC;
import ModelDaoImp.UsuarioDaoJDBC;
import db.DB;

public class DaoFactory {
    public static ContratoDao createContratoDao(){
        return new ContratoDaoJDBC(DB.getConnection());

    }
    public static UsuarioDao createUsuarioDao(){
        return new UsuarioDaoJDBC(DB.getConnection());
    }
}
