package frb.edu.br.filipe.dominio.contratos;

import frb.edu.br.filipe.dominio.entidades.CidadeDto;
import java.util.List;

public interface ICidade {
    boolean incluir(CidadeDto cidade);
    boolean alterar(CidadeDto cidade);
    boolean deletar(int id);
    CidadeDto getRegistroPorId(int id);
    List<CidadeDto> getListaTodos();
}
