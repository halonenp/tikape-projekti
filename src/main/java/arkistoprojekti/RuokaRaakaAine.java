/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkistoprojekti;

public class RuokaRaakaAine {

    Integer id;
    Integer ruokaid;
    Integer raakaaineid;
    Integer jarjestys;
    Integer maara;
    String ohje;

    public RuokaRaakaAine(Integer id, Integer ruokaid, Integer raakaaineid, Integer jarjestys, Integer maara, String ohje) {
        this.id = id;
        this.ruokaid = ruokaid;
        this.raakaaineid = raakaaineid;
        this.jarjestys = jarjestys;
        this.maara = maara;
        this.ohje = ohje;
    }

    public Integer getRuokaid() {
        return ruokaid;
    }

    public Integer getRaakaAineid() {
        return raakaaineid;
    }

    public String getOhje() {
        return ohje;
    }

    public Integer getJarjestys() {
        return jarjestys;
    }

    public Integer getMaara() {
        return maara;
    }
}
