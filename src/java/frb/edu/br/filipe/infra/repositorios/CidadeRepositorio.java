package frb.edu.br.filipe.infra.repositorios;

import frb.edu.br.filipe.dominio.contratos.ICidade;
import frb.edu.br.filipe.dominio.entidades.CidadeDto;
import frb.edu.br.filipe.infra.data.DaoUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CidadeRepositorio extends DaoUtil implements ICidade{

    public CidadeRepositorio() {
        super();
    }
    
    @Override
    public boolean incluir(CidadeDto cidade) {
        String sql = "INSERT INTO cidade (cidade, pais_id, ultima_atualizacao)" + 
                     " VALUES (?, ?, ?)";
        PreparedStatement ps;
        int ret = -1;
        try {
            ps = super.getPreparedStatement(sql);
            ps.setString(1, cidade.getCidade());
            ps.setInt(2, cidade.getPais().getPais_id());
            ps.setTimestamp(3, cidade.getUltima_atualizacao());
            ret = ps.executeUpdate();
            ps.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CidadeRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CidadeRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return ret > 0;
    }

    @Override
    public boolean alterar(CidadeDto cidade) {
        String sql = "UPDATE cidade SET cidade=?, pais_id=?, ultima_atualizacao=?" + 
                     " WHERE cidade_id=?";
        PreparedStatement ps;
        int ret = -1;
        try {
            ps = super.getPreparedStatement(sql);
            ps.setString(1, cidade.getCidade());
            ps.setInt(2, cidade.getPais().getPais_id());
            ps.setTimestamp(3, cidade.getUltima_atualizacao());
            ps.setInt(4, cidade.getCidade_id());
            ret = ps.executeUpdate();
            ps.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CidadeRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CidadeRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return ret > 0;
    }

    @Override
    public boolean deletar(int id) {
       String sql = "DELETE FROM cidade WHERE cidade_id=?";
        PreparedStatement ps;
        int ret = -1;
        try {
            ps = super.getPreparedStatement(sql);
            ps.setInt(1, id);
            ret = ps.executeUpdate();
            ps.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CidadeRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CidadeRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return ret > 0;
    }

    @Override
    public CidadeDto getRegistroPorId(int id) {
       CidadeDto cidade = new CidadeDto();
       String sql = "SELECT cidade_id, cidade, pais_id, ultima_atualizacao FROM cidade";
       sql += " WHERE cidade_id = ?";
       PaisRepositorio pais = new PaisRepositorio();
        try {
            PreparedStatement ps = super.getPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cidade = new CidadeDto(rs.getInt("cidade_id"), 
                                       rs.getString("cidade"),
                                       pais.getRegistroPorId(rs.getInt("pais_id")),
                                       rs.getTimestamp("ultima_atualizacao"));
            }
            rs.close();
            ps.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CidadeRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CidadeRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        }
              
       return cidade;
    }

    @Override
    public List<CidadeDto> getListaTodos() {
       List<CidadeDto> cidades = new LinkedList<CidadeDto>();
       String sql = "SELECT cidade_id, cidade, pais_id, ultima_atualizacao FROM cidade";
       PaisRepositorio pais = new PaisRepositorio();
        try {
            PreparedStatement ps = super.getPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cidades.add(new CidadeDto(rs.getInt("cidade_id"), 
                                          rs.getString("cidade"),
                                          pais.getRegistroPorId(rs.getInt("pais_id")),
                                          rs.getTimestamp("ultima_atualizacao")));
            }
            rs.close();
            ps.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CidadeRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CidadeRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        }
              
       return cidades;
    }
    
}
