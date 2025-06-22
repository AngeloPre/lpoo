package service;


import java.util.Arrays;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import model.Automovel;
import model.Motocicleta;
import model.Van;
import model.Veiculo;
import model.enums.Categoria;
import model.enums.Marca;
import table.VeiculoTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author mrblue
 */
public class VeiculoService {

    
    public static String[] obterTiposVeiculo() {
        // se você não tiver um enum específico para tipo, devolve “na mão”
        return new String[]{"AUTOMOVEL", "MOTOCICLETA", "VAN"};
    }

        public static String[] obterMarcas() {
        return Arrays.stream(Marca.values())
                     .map(marca -> marca.name())
                     .toArray(size -> new String[size]);
    }

    public static String[] obterCategorias() {
        return Arrays.stream(Categoria.values())
                     .map(categoria -> categoria.name())
                     .toArray(size -> new String[size]);
    }
    public static void aplicarFiltrosVeiculo(TableRowSorter<VeiculoTableModel> veiculoSorter, JCheckBox checkboxFiltroTipo, JCheckBox checkboxFiltroMarca, JCheckBox checkboxFiltroCategoria, JComboBox<String> comboCategoria, JComboBox<String> comboMarca, JComboBox<String> comboTipo) {
        // 1) cria o filtro
        RowFilter<VeiculoTableModel, Integer> filtro = new RowFilter<VeiculoTableModel, Integer>() {
            @Override
            public boolean include(RowFilter.Entry<? extends VeiculoTableModel, ? extends Integer> e) {
                VeiculoTableModel model = e.getModel(); // M
                int rowIndex = e.getIdentifier(); // I
                Veiculo v = model.getVeiculo(rowIndex);
                /* ---------- filtro por TIPO ---------- */
                if (checkboxFiltroTipo.isSelected()) {
                    String tipo = comboTipo.getSelectedItem().toString();
                    if ("AUTOMOVEL".equals(tipo) && !(v instanceof Automovel)) {
                        return false;
                    }
                    if ("MOTOCICLETA".equals(tipo) && !(v instanceof Motocicleta)) {
                        return false;
                    }
                    if ("VAN".equals(tipo) && !(v instanceof Van)) {
                        return false;
                    }
                }
                /* ---------- filtro por MARCA ---------- */
                if (checkboxFiltroMarca.isSelected()) {
                    String marca = comboMarca.getSelectedItem().toString();
                    if (!v.getMarca().name().equalsIgnoreCase(marca)) {
                        return false;
                    }
                }
                /* ---------- filtro por CATEGORIA ---------- */
                if (checkboxFiltroCategoria.isSelected()) {
                    String cat = comboCategoria.getSelectedItem().toString();
                    if (v instanceof Automovel a) {
                        if (!a.getCategoria().name().equalsIgnoreCase(cat)) {
                            return false;
                        }
                    } else {
                        return false; // não é automóvel → descarta
                    }
                }
                return true; // passou em todos os filtros marcados
            }
        };
        // 2) aplica no sorter
        veiculoSorter.setRowFilter(filtro);
    }
}
