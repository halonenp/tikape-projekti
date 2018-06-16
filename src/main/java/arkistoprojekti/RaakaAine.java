/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkistoprojekti;

public class RaakaAine {

    String nimi;
    Integer id;

    public RaakaAine(Integer id, String nimi) {
        this.nimi = nimi;
        this.id = id;

    }

    public Integer getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }
}
