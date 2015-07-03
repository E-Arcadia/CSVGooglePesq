import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class Controle {
	File arqGoogle = null;
	File arqSaida = null;
	BufferedReader arqLeitura = null;
	OutputStreamWriter arqConvertido = null;
	ArrayList<String> cabecalho = null;
	int nDaLinha = 0;
	char chDelimitador = '\t';
	char chFimLinha = '\n';

	public Controle() {
		// Abre o arquivo de leitura
		String ret = InterfaceUsuario.localizaArquivoGoogle();
		if (ret != null) {
			arqGoogle = new File(ret);
			boolean controle = false;
			try {
				arqLeitura = new BufferedReader(new InputStreamReader(
						new FileInputStream(arqGoogle), "UTF-8"));
				controle = true;
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				System.out.println("Erro ao abri arquivo " + arqGoogle
						+ " Constructor Controle.");
			}

			// Cria o arquivo de saída
			ret = InterfaceUsuario.localArquivoConvertido();
			if (ret != null) {
				try {
					arqConvertido = new OutputStreamWriter(
							new FileOutputStream(new File(ret)), "UTF-8");
				} catch (IOException e1) {
					System.out.println("Erro ao criar arquivo convertido.");
				}
			}

			// Faz a leitura do arquivo
			if (controle) {
				// lê o cabeçalho
				cabecalho = leUmaLinha();

				ArrayList<String> linha = null;
				try {
					while (arqLeitura.ready()) {
						linha = leUmaLinha();
						InterfaceUsuario.mostra(cabecalho, linha, nDaLinha);
						salvaUmRegistro(cabecalho, linha, nDaLinha);
					}
					arqLeitura.close();
					arqConvertido.close();
				} catch (IOException e) {
					System.out.println("ERRO");
				}
			}
		}

	}

	private void salvaUmRegistro(ArrayList<String> cabecalho,
			ArrayList<String> linha, int reg) {
		for (int idx = 0; idx < linha.size(); idx++) {
			String cab = cabecalho.get(idx);
			String lin = linha.get(idx);
			try {
				arqConvertido.write(reg + " "+ chDelimitador + cab + chDelimitador + lin + chFimLinha);
			} catch (IOException e) {
				System.out.println("Erro ao salvar um registro.");
			}
		}
	}

	private ArrayList<String> leUmaLinha() {
		String linha = null;
		try {
			linha = arqLeitura.readLine();
			nDaLinha++;
		} catch (IOException e) {
			System.out.println("Erro ao ler cabeçalho " + arqGoogle
					+ " - Linha " + nDaLinha);
		}
		String[] cab = linha.split(String.valueOf(chDelimitador));
		return new ArrayList<String>(Arrays.asList(cab));
	}

}
