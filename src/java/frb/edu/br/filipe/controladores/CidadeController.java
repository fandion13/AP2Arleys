package frb.edu.br.filipe.controladores;

import frb.edu.br.filipe.dominio.entidades.CidadeDto;
import frb.edu.br.filipe.infra.repositorios.CidadeRepositorio;
import java.sql.Timestamp;
import java.util.List;

public class CidadeController {
    private CidadeDto cidade;
    private List<CidadeDto> cidades = null;
    
    private CidadeRepositorio cidadeRepositorio = new CidadeRepositorio();
    
    public CidadeController() {
    }

    public CidadeDto getCidade() {
        return cidade;
    }

    public void setCidade(CidadeDto cidade) {
        this.cidade = cidade;
    }

    public List<CidadeDto> getCidades() {
        if(cidades == null){
            cidades = cidadeRepositorio.getListaTodos();
        }
        return cidades;
    }
    
    public String prepararInlcusao(){
        cidade = new CidadeDto();
        return "vaiParaIncluir";
    }
    
    public String finalizaInclusao(){
        cidade.setUltima_atualizacao(new Timestamp(System.currentTimeMillis()));
        cidadeRepositorio.incluir(cidade);
        cidades = null;
        
        return "voltaParaListagemCidade";
    }
    
    public String finalizaEdicao(){
        cidade.setUltima_atualizacao(new Timestamp(System.currentTimeMillis()));
        cidadeRepositorio.alterar(cidade);
        cidades = null;
        
        return "voltaParaListagemCidade";
    }
    
    public String finalizaDelecao(){
        cidadeRepositorio.deletar(cidade.getCidade_id());        
        cidades = null;
        
        return "refresh";
    }
}