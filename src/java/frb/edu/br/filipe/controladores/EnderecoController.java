package frb.edu.br.filipe.controladores;

import frb.edu.br.filipe.dominio.entidades.EnderecoDto;
import frb.edu.br.filipe.infra.repositorios.EnderecoRepositorio;
import java.sql.Timestamp;
import java.util.List;

public class EnderecoController {
    private EnderecoDto endereco;
    private List<EnderecoDto> enderecos = null;
    
    private EnderecoRepositorio enderecoRepositorio = new EnderecoRepositorio();
    
    public EnderecoController() {
    }

    public EnderecoDto getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDto endereco) {
        this.endereco = endereco;
    }

    public List<EnderecoDto> getEnderecos() {
        if(enderecos == null){
            enderecos = enderecoRepositorio.getListaTodos();
        }
        return enderecos;
    }
    
    public String prepararInlcusao(){
        endereco = new EnderecoDto();
        return "vaiParaIncluir";
    }
    
    public String finalizaInclusao(){
        endereco.setUltima_atualizacao(new Timestamp(System.currentTimeMillis()));
        enderecoRepositorio.incluir(endereco);
        enderecos = null;
        
        return "voltaParaListagemEndereco";
    }
    
    public String finalizaEdicao(){
        endereco.setUltima_atualizacao(new Timestamp(System.currentTimeMillis()));
        enderecoRepositorio.alterar(endereco);
        enderecos = null;
        
        return "voltaParaListagemEndereco";
    }
    
    public String finalizaDelecao(){
        enderecoRepositorio.deletar(endereco.getEndereco_id());        
        enderecos = null;
        
        return "refresh";
    }
}