package Account;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Cecília Assis
 */
public class TableModel{
    
    private StringProperty tabData;
    private StringProperty tabDescricao;
    private StringProperty tabCategoria;
    private StringProperty tabValor;

    public TableModel(String tabData, String tabDescricao, String tabCategoria, String tabValor) {
        this.tabData = new SimpleStringProperty (tabData);
        this.tabDescricao = new SimpleStringProperty (tabDescricao);
        this.tabCategoria = new SimpleStringProperty (tabCategoria) ;
        this.tabValor = new SimpleStringProperty (tabValor);
    }

    public TableModel() {
    }

    public StringProperty tabDataProperty() {
        return tabData;
    }

    public StringProperty tabDescricaoProperty() {
        return tabDescricao;
    }

    public StringProperty tabCategoriaProperty() {
        return tabCategoria;
    }

    public StringProperty tabValorProperty() {
        return tabValor;
    }   
    
}
