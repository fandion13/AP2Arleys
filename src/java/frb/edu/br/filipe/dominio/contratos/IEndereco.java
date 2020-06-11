package frb.edu.br.filipe.dominio.contratos;

import frb.edu.br.filipe.dominio.entidades.EnderecoDto;
import java.util.List;

public interface IEndereco {
    boolean incluir(EnderecoDto endereco);
    boolean alterar(EnderecoDto endereco);
    boolean deletar(int id);
    EnderecoDto getRegistroPorId(int id);
    List<EnderecoDto> getListaTodos();
}
