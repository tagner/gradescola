/*
 * CondicoesBasicas.java
 * Criado em 2009/12/04 - 11:43
 */
package br.usp.gradescola.condicoes;

import br.usp.gradescola.estrutura.Condicao;
import br.usp.gradescola.estrutura.Grade;

import java.math.BigDecimal;

import java.util.Arrays;

/**
 * Contém funções utilitárias para a utilização de condições booleanas
 * e aritméticas comuns.
 * @author Victor Williams Stafusa da Silva
 */
public final class CondicoesBasicas {

    /**
     * Construtor privado para impedir a instanciação. Esta classe não
     * deve ser instanciável.
     */
    private CondicoesBasicas() {}

    private static final class CondicaoAnd implements Condicao.Booleana {

        private final Iterable<Condicao.Booleana> condicoes;

        public CondicaoAnd(Iterable<Condicao.Booleana> condicoes) {
            this.condicoes = condicoes;
        }

        @Override
        public boolean avaliar(Grade grade) {
            for (Condicao.Booleana condicao : condicoes) {
                if (!condicao.avaliar(grade)) return false;
            }
            return true;
        }
    }

    /**
     * Cria uma condição booleana que combina diversas condições
     * booleanas dadas por meio do operador booleano AND.
     * @param condicoes As condições a serem combinadas.
     * @return Uma condição que combina (por meio do operador AND) as
     * condições dadas.
     */
    public static Condicao.Booleana and(Iterable<Condicao.Booleana> condicoes) {
        return new CondicaoAnd(condicoes);
    }

    /**
     * Cria uma condição booleana que combina diversas condições
     * booleanas dadas por meio do operador booleano AND.
     * @param condicoes As condições a serem combinadas.
     * @return Uma condição que combina (por meio do operador AND) as
     * condições dadas.
     */
    public static Condicao.Booleana and(Condicao.Booleana... condicoes) {
        return and(Arrays.asList(condicoes));
    }

    private static final class CondicaoOr implements Condicao.Booleana {

        private final Iterable<Condicao.Booleana> condicoes;

        public CondicaoOr(Iterable<Condicao.Booleana> condicoes) {
            this.condicoes = condicoes;
        }

        @Override
        public boolean avaliar(Grade grade) {
            for (Condicao.Booleana condicao : condicoes) {
                if (condicao.avaliar(grade)) return true;
            }
            return false;
        }
    }

    /**
     * Cria uma condição booleana que combina diversas condições
     * booleanas dadas por meio do operador booleano OR.
     * @param condicoes As condições a serem combinadas.
     * @return Uma condição que combina (por meio do operador OR) as
     * condições dadas.
     */
    public static Condicao.Booleana or(Iterable<Condicao.Booleana> condicoes) {
        return new CondicaoOr(condicoes);
    }

    /**
     * Cria uma condição booleana que combina diversas condições
     * booleanas dadas por meio do operador booleano OR.
     * @param condicoes As condições a serem combinadas.
     * @return Uma condição que combina (por meio do operador OR) as
     * condições dadas.
     */
    public static Condicao.Booleana or(Condicao.Booleana... condicoes) {
        return or(Arrays.asList(condicoes));
    }

    private static final class CondicaoNot implements Condicao.Booleana {
        private final Condicao.Booleana condicao;

        public CondicaoNot(Condicao.Booleana condicao) {
            this.condicao = condicao;
        }

        @Override
        public boolean avaliar(Grade grade) {
            return !condicao.avaliar(grade);
        }
    }

    /**
     * Cria uma condição booleana que quando avaliada, inverte a
     * resposta de uma outra condição booleana dada.
     * @param condicoes A condição dada.
     * @return Uma condição que inverte a avaliação de uma outra
     * condição.
     */
    public static Condicao.Booleana not(Condicao.Booleana condicao) {
        return new CondicaoNot(condicao);
    }

    private static final class Contagem implements Condicao.Numerica {

        private final Iterable<Condicao.Booleana> condicoes;

        public Contagem(Iterable<Condicao.Booleana> condicoes) {
            this.condicoes = condicoes;
        }

        @Override
        public BigDecimal avaliar(Grade grade) {
            int verdadeiras = 0;
            for (Condicao.Booleana condicao : condicoes) {
                if (condicao.avaliar(grade)) verdadeiras++;
            }
            return BigDecimal.valueOf(verdadeiras);
        }
    }

    /**
     * Cria uma condição numérica que quando avaliada, fornece a
     * quantidade das condições booleanas dadas que são verdadeiras.
     * Em outras palavras, conta quantas condições são verdadeiras.
     * @param condicoes As condições a serem avaliadas.
     * @return Uma condição que quando avaliada, conta quantas das
     * condições dadas são verdadeiras.
     */
    public static Condicao.Numerica contagem(Iterable<Condicao.Booleana> condicoes) {
        return new Contagem(condicoes);
    }

    /**
     * Cria uma condição numérica que quando avaliada, fornece a
     * quantidade das condições booleanas dadas que são verdadeiras.
     * Em outras palavras, conta quantas condições são verdadeiras.
     * @param condicoes As condições a serem avaliadas.
     * @return Uma condição que quando avaliada, conta quantas das
     * condições dadas são verdadeiras.
     */
    public static Condicao.Numerica contagem(Condicao.Booleana... condicoes) {
        return contagem(Arrays.asList(condicoes));
    }

    private static final class BooleanaParaNumerica implements Condicao.Numerica {
        private final Condicao.Booleana condicao;

        public BooleanaParaNumerica(Condicao.Booleana condicao) {
            this.condicao = condicao;
        }

        @Override
        public BigDecimal avaliar(Grade grade) {
            return condicao.avaliar(grade) ? BigDecimal.ONE : BigDecimal.ZERO;
        }
    }

    /**
     * Cria uma condição numérica que quando avaliada, retorna 1 se
     * a avaliação de uma dada condição booleana for verdadeira e 0
     * caso contrário.
     * @param condicoes A condição a serem avaliadas.
     * @return Uma condição que quando avaliada, retorna 1 se a
     * condição dada for verdadeira e 0 se não for.
     */
    public static Condicao.Numerica valor(Condicao.Booleana condicao) {
        return new BooleanaParaNumerica(condicao);
    }

    private static final class CondicaoMultiplica implements Condicao.Numerica {

        private final Iterable<Condicao.Numerica> condicoes;

        public CondicaoMultiplica(Iterable<Condicao.Numerica> condicoes) {
            this.condicoes = condicoes;
        }

        @Override
        public BigDecimal avaliar(Grade grade) {
            BigDecimal resposta = BigDecimal.ONE;
            for (Condicao.Numerica condicao : condicoes) {
                resposta = resposta.multiply(condicao.avaliar(grade));
            }
            return resposta;
        }
    }

    /**
     * Cria uma condição numérica que combina diversas condições
     * numéricas dadas multiplicando o valor dado pela avaliação delas.
     * @param condicoes As condições a serem combinadas.
     * @return Uma condição que multiplica o valor da avaliação das
     * condições dadas.
     */
    public static Condicao.Numerica multiplicar(Iterable<Condicao.Numerica> condicoes) {
        return new CondicaoMultiplica(condicoes);
    }

    /**
     * Cria uma condição numérica que combina diversas condições
     * numéricas dadas multiplicando o valor dado pela avaliação delas.
     * @param condicoes As condições a serem combinadas.
     * @return Uma condição que multiplica o valor da avaliação das
     * condições dadas.
     */
    public static Condicao.Numerica multiplicar(Condicao.Numerica... condicoes) {
        return multiplicar(Arrays.asList(condicoes));
    }

    private static final class CondicaoSoma implements Condicao.Numerica {

        private final Iterable<Condicao.Numerica> condicoes;

        public CondicaoSoma(Iterable<Condicao.Numerica> condicoes) {
            this.condicoes = condicoes;
        }

        @Override
        public BigDecimal avaliar(Grade grade) {
            BigDecimal resposta = BigDecimal.ONE;
            for (Condicao.Numerica condicao : condicoes) {
                resposta = resposta.add(condicao.avaliar(grade));
            }
            return resposta;
        }
    }

    /**
     * Cria uma condição numérica que combina diversas condições
     * numéricas dadas somando o valor dado pela avaliação delas.
     * @param condicoes As condições a serem combinadas.
     * @return Uma condição que soma o valor da avaliação das
     * condições dadas.
     */
    public static Condicao.Numerica somar(Iterable<Condicao.Numerica> condicoes) {
        return new CondicaoSoma(condicoes);
    }

    /**
     * Cria uma condição numérica que combina diversas condições
     * numéricas dadas somando o valor dado pela avaliação delas.
     * @param condicoes As condições a serem combinadas.
     * @return Uma condição que soma o valor da avaliação das
     * condições dadas.
     */
    public static Condicao.Numerica somar(Condicao.Numerica... condicoes) {
        return somar(Arrays.asList(condicoes));
    }

    private static final class CondicaoMenos implements Condicao.Numerica {

        private final Condicao.Numerica condicao;

        public CondicaoMenos(Condicao.Numerica condicao) {
            this.condicao = condicao;
        }

        @Override
        public BigDecimal avaliar(Grade grade) {
            return condicao.avaliar(grade).negate();
        }
    }

    /**
     * Cria uma condição numérica que quando avaliada, inverte o sinal
     * da avaliação de uma outra condição numérica dada.
     * @param condicoes A condição dada.
     * @return Uma condição que inverte a avaliação de uma outra
     * condição.
     */
    public static Condicao.Numerica negar(Condicao.Numerica condicao) {
        return new CondicaoMenos(condicao);
    }

    private static final class ConstanteNumerica implements Condicao.Numerica {

        private final BigDecimal constante;

        public ConstanteNumerica(BigDecimal constante) {
            this.constante = constante;
        }

        @Override
        public BigDecimal avaliar(Grade grade) {
            return constante;
        }
    }

    /**
     * Cria uma condição numérica que quando avaliada, retorna sempre
     * um mesmo valor. Tal valor é informado pelo parâmetro.
     * @param constante O valor a ser retornado quando a condição
     * retornada é avaliada.
     * @return Uma condição que quando avaliada, retorna o valor
     * informado no parâmetro.
     */
    public static Condicao.Numerica valor(BigDecimal constante) {
        return new ConstanteNumerica(constante);
    }
}