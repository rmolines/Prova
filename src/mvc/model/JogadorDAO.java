

package mvc.model;


import java.sql.Date;
import java.sql.*;
import java.util.*;


public class JogadorDAO {


    private Connection connection = null;
	
    public JogadorDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost/dados_prova", "root", "DRose1");
        } catch (SQLException | ClassNotFoundException e) {e.printStackTrace();}
    }


    public void adicionaUsuario(Jogador Jogador) {
        try {
            String sql = "INSERT INTO jogador (usuario) values(?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,Jogador.getUsuario());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {e.printStackTrace();}
    }


    public void adiciona(Jogador Jogador) {
        try {
            String sql = "INSERT INTO Jogador" +
                         "(Usuario,finalizado,dataFinalizacao) values(?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,Jogador.getUsuario());
            stmt.setBoolean(2, Jogador.isFinalizado());
            stmt.setDate(3, new Date(Jogador.getDataFinalizacao().getTimeInMillis()));
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {e.printStackTrace();}
    }
	
    public List<Jogador> getLista() {
        List<Jogador> Jogadors = new ArrayList<Jogador>();
        try {	
            PreparedStatement stmt = connection.
					prepareStatement("SELECT * FROM Jogador");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Jogador Jogador = new Jogador();
                Jogador.setId(rs.getLong("id"));
                Jogador.setUsuario(rs.getString("Usuario"));
                Jogador.setFinalizado(rs.getBoolean("finalizado"));
                Calendar data = Calendar.getInstance();
                Date dataFinalizacao = rs.getDate("dataFinalizacao");
                if(dataFinalizacao!=null) {		
                    data.setTime(dataFinalizacao);
                    Jogador.setDataFinalizacao(data);
                }
                Jogadors.add(Jogador);
            }
            rs.close();
            stmt.close();
        } catch(SQLException e) {System.out.println(e);}
        return Jogadors;
    }
	
    public void remove(Jogador Jogador) {
        try {
            PreparedStatement stmt = connection
	                .prepareStatement("DELETE FROM Jogador WHERE id=?");
            stmt.setLong(1, Jogador.getId());
            stmt.execute();
            stmt.close();
        } catch(SQLException e) {System.out.println(e);}
    }


    public Jogador buscaPorId(Long id) {
        Jogador Jogador = new Jogador();
        try {	
            PreparedStatement stmt = connection.
                                    prepareStatement("SELECT * FROM Jogador WHERE id=? ");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Jogador.setId(rs.getLong("id"));
                Jogador.setUsuario(rs.getString("Usuario"));
                Jogador.setFinalizado(rs.getBoolean("finalizado"));
                Calendar data = Calendar.getInstance();
                Date dataFinalizacao = rs.getDate("dataFinalizacao");
                if(dataFinalizacao!=null) {		
                    data.setTime(dataFinalizacao);
                    Jogador.setDataFinalizacao(data);
                }
            }
            rs.close();
            stmt.close();
        } catch(SQLException e) {System.out.println(e);}
        return Jogador;
    }


    public void altera(Jogador Jogador) {
        try {
            String sql = "UPDATE Jogador SET Usuario=?, finalizado=?, " +
            "dataFinalizacao=? WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, Jogador.getUsuario());
            stmt.setBoolean(2, Jogador.isFinalizado());
            if(Jogador.getDataFinalizacao()!=null) {
            	stmt.setDate(3, new Date(Jogador.getDataFinalizacao().getTimeInMillis()));
            } else {
            	stmt.setDate(3, new Date(Calendar.getInstance().getTimeInMillis()));
            }
            stmt.setLong(4, Jogador.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch(SQLException e) {System.out.println(e);}
    }
	
    public void finaliza(Long id) {
        try {
            String sql = "UPDATE Jogador SET finalizado=?, dataFinalizacao=? " +
		            "WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
            stmt.setLong(3, id);
            stmt.executeUpdate();
            stmt.close();
        } catch(SQLException e) {System.out.println(e);}
    }


    public void close() {
        try { connection.close();}
        catch (SQLException e) {e.printStackTrace();}		
    }


}
