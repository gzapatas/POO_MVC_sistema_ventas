/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuraciones;

import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author gzapata
 */
public interface ModelInterface {

    /**
     *
     * @param env
     * @return
     */
    public boolean loadModel(Dotenv env);
}
