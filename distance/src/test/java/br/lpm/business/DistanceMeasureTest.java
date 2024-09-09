package br.lpm.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DistanceMeasureTest {

    private Dataset dataset;
    private Pessoa usuario1;
    private Pessoa usuario2;
    private Pessoa usuario3;
    private Pessoa usuarioReplicaA;
    private DistanceMeasure distanceMeasure;

    @BeforeEach
    public void setUp() {
        usuario1 = new Pessoa(
                "Gabriela",
                LocalDate.of(2006, 1, 15),
                Genero.FEMININO,
                1.53f,
                58,
                6000.54f,
                "Belo Horizonte",
                Hobby.LIVRO,
                EstadoCivil.CASADO,
                Escolaridade.MEDIO,
                true,
                Moradia.CASA_PROPRIA);

        usuario2 = new Pessoa(
                "Carlos",
                LocalDate.of(2005, 10, 20),
                Genero.MASCULINO,
                1.72f,
                80,
                1000.12f,
                "Almenara",
                Hobby.ESPORTE,
                EstadoCivil.CASADO,
                Escolaridade.MEDIO,
                true,
                Moradia.ALUGUEL);

        usuario3 = new Pessoa(
                "Leonardo",
                LocalDate.of(1976, 9, 4),
                Genero.MASCULINO,
                1.69f,
                62,
                2000.12f,
                "Licínio de Almeida",
                Hobby.ESPORTE,
                EstadoCivil.SOLTEIRO,
                Escolaridade.POS_GRADUACAO,
                false,
                Moradia.CASA_PROPRIA);

        usuarioReplicaA = new Pessoa(
                "Gabriela",
                LocalDate.of(2006, 1, 15),
                Genero.FEMININO,
                1.53f,
                58,
                6000.54f,
                "Belo Horizonte",
                Hobby.LIVRO,
                EstadoCivil.CASADO,
                Escolaridade.MEDIO,
                true,
                Moradia.CASA_PROPRIA);

        dataset = new Dataset();
        dataset.addPessoa(usuario1);
        dataset.addPessoa(usuario2);
        dataset.addPessoa(usuario3);
        dataset.addPessoa(usuarioReplicaA);

        distanceMeasure = new DistanceMeasure(dataset);
    }

    @Test
    @DisplayName("Testando normalização dos campos para diferentes atributos")
    public void testNormalizeFields() {
        float[] normalizacaoAlturas = distanceMeasure.normalizeField("Altura");
        assertEquals(0.0f, normalizacaoAlturas[0], "Valor normalizado para altura da usuario1 deve ser 0.0");

        float[] normalizacaoPesos = distanceMeasure.normalizeField("Peso");
        assertEquals(0.0f, normalizacaoPesos[0], "Valor normalizado para peso da usuario1 deve ser 0.0");

        float[] normalizacaoIdades = distanceMeasure.normalizeField("Idade");
        assertEquals(0.0f, normalizacaoIdades[0], "Valor normalizado para idade da usuario1 deve ser 0.0");

        float[] normalizacaoRendas = distanceMeasure.normalizeField("Renda");
        assertEquals(1.0f, normalizacaoRendas[0], "Valor normalizado para renda da usuario1 deve ser 1.0");

        
    }

    @Test
    @DisplayName("Testando a distância calculada entre duas pessoas")
    public void testCalcDistance() {
    dataset.addPessoa(usuario1);
      dataset.addPessoa(usuario2);
      float distancia1 = distanceMeasure.calcularDistancia(usuario1, usuario2);
      assertEquals(0.774f, distancia1, 0.01f, "A distância entre Gabriela e Carlos deve ser aproximadamente 0.837");
      dataset.addPessoa(usuarioReplicaA);
      float distancia2 = distanceMeasure.calcularDistancia(usuario1, usuarioReplicaA);
      assertEquals(0.0f, distancia2, 0.01f, "A distância entre pessoas idênticas deve ser 0.0");
    }
  }
