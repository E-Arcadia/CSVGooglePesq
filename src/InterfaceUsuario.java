import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class InterfaceUsuario {

	public static String localizaArquivoGoogle() {
		JFileChooser jfilechooser = new JFileChooser();
		jfilechooser.setDialogTitle("Localizar Arquivo CSV - Google");
		int opc = jfilechooser.showOpenDialog(null);
		String opcao = null;

		switch (opc) {
		case JFileChooser.CANCEL_OPTION:
			JOptionPane.showMessageDialog(null, "Operação Cancelada");
			break;
		case JFileChooser.APPROVE_OPTION:
			opcao = jfilechooser.getSelectedFile().getAbsolutePath();
			break;
		case JFileChooser.ERROR_OPTION:
			JOptionPane.showMessageDialog(null, "Erro na Operação");
			break;
		}
		return opcao;
	}

	public static String localArquivoConvertido() {
		JFileChooser jfilechooser = new JFileChooser();
		int retorno = jfilechooser.showSaveDialog(null);
		jfilechooser.setDialogTitle("Determina Arquivo Convertido");
		String opcao = null;

		switch (retorno) {
		case JFileChooser.CANCEL_OPTION:
			JOptionPane.showMessageDialog(null, "Operação Cancelada");
			break;
		case JFileChooser.APPROVE_OPTION:
			opcao = jfilechooser.getSelectedFile().getAbsolutePath();
			break;
		case JFileChooser.ERROR_OPTION:
			JOptionPane.showMessageDialog(null, "Erro na Operação");
			break;
		}
		return opcao;
	}

	public static void mostra(ArrayList<String> cabecalho,
			ArrayList<String> linha, int nLinhas) {
		for (int idx = 0; idx < linha.size();idx++) {
			String cab = cabecalho.get(idx);
			String lin = linha.get(idx);
			System.out.println(nLinhas + " - " + cab + " --> " + lin);
		}
	}
}
