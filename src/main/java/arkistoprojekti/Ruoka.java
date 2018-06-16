/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkistoprojekti;

public class Ruoka {

    String ruoannimi;
    Integer id;

    public Ruoka(Integer id, String ruoannimi) {
        this.ruoannimi = ruoannimi;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getRuoannimi() {
        return ruoannimi;
    }
}
