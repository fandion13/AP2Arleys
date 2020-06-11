package frb.edu.br.filipe.controladores;

import frb.edu.br.filipe.dominio.entidades.PaisDto;
import frb.edu.br.filipe.infra.repositorios.PaisRepositorio;
import java.sql.Timestamp;
import java.util.List;

public class PaisController {
    private PaisDto pais;
    private List<PaisDto> paises = null;
    
    private PaisRepositorio paisRepositorio = new PaisRepositorio();
    
    public PaisController() {
    }

    public PaisDto getPais() {
        return pais;
    }

    public void setPais(PaisDto pais) {
        this.pais = pais;
    }

    public List<PaisDto> getPaises() {
        if(paises == null){
            paises = paisRepositorio.getListaTodos();
        }
        return paises;
    }
    
    public String prepararInlcusao(){
        pais = new PaisDto();
        return "vaiParaIncluir";
    }
    
    public String finalizaInclusao(){
        pais.setUltima_atualizacao(new Timestamp(System.currentTimeMillis()));
        paisRepositorio.incluir(pais);
        paises = null;
        
        return "voltaParaListagemPais";
    }
    
    public String finalizaEdicao(){
        pais.setUltima_atualizacao(new Timestamp(System.currentTimeMillis()));
        paisRepositorio.alterar(pais);
        paises = null;
        
        return "voltaParaListagemPais";
    }
    
    public String finalizaDelecao(){
        paisRepositorio.deletar(pais.getPais_id());        
        paises = null;
        
        return "refresh";
    }
}