/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmancecilia;

/**
 *
 * @author Cecília Assis
 */
public class NoTab extends No {
    private String code;
    

    public NoTab(char letra, String code ) {
        this.code = code;
        super.setLetra(letra); //funciona? vamor ver
    }
    
    public NoTab(){
        
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
}
