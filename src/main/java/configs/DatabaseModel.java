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
public class DatabaseModel implements ModelInterface {
    private String Name;
    private String User;
    private String Password;
    private String Port;
    private String Ip;

    public String getIp() {
        return Ip;
    }

    public void setIp(String Ip) {
        this.Ip = Ip;
    }

    public String getPort() {
        return Port;
    }

    public void setPort(String Port) {
        this.Port = Port;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    @Override
    public boolean loadModel(Dotenv env) {
        this.setName(env.get("DATABASE_NAME"));
        this.setUser(env.get("DATABASE_USER"));
        this.setPassword(env.get("DATABASE_PASSWORD"));
        this.setPort(env.get("DATABASE_PORT"));
        this.setIp(env.get("DATABASE_IP"));
        
        return true;
    }
    
}
