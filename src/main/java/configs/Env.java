/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configs;

import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author gzapata
 */
public class Env {
    public DatabaseModel DbModel;
    private static Env instance;
    
    public static Env getInstance(){
        if(instance == null){
            instance = new Env();
        }
        return instance;
    }
    
    Env(){
        DbModel = new DatabaseModel();
    }
    
    public boolean InitEnv(){
        Dotenv env = Dotenv.configure().directory(".").load();
        
        if(!DbModel.loadModel(env)){
            return false;
        }
        
        return true;
    }
}
