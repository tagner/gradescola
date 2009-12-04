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
 * @author Victor Williams Stafusa da Silva
 */
public final class CondicoesBasicas {

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

    public static Condicao.Booleana and(Iterable<Condicao.Booleana> condicoes) {
        return new CondicaoAnd(condicoes);
    }

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

    public static Condicao.Booleana or(Iterable<Condicao.Booleana> condicoes) {
        return new CondicaoOr(condicoes);
    }

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

    public static Condicao.Booleana not(Condicao.Booleana condicao) {
        return new CondicaoNot(condicao);
    }

    private static final class Contagem implements Condicao.Real {

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

    public static Condicao.Real contagem(Iterable<Condicao.Booleana> condicoes) {
        return new Contagem(condicoes);
    }

    public static Condicao.Real contagem(Condicao.Booleana... condicoes) {
        return contagem(Arrays.asList(condicoes));
    }

    private static final class BooleanaParaNumerica implements Condicao.Real {
        private final Condicao.Booleana condicao;

        public BooleanaParaNumerica(Condicao.Booleana condicao) {
            this.condicao = condicao;
        }

        @Override
        public BigDecimal avaliar(Grade grade) {
            return condicao.avaliar(grade) ? BigDecimal.ONE : BigDecimal.ZERO;
        }
    }

    public static Condicao.Real valor(Condicao.Booleana condicao) {
        return new BooleanaParaNumerica(condicao);
    }

    private static final class CondicaoMultiplica implements Condicao.Real {

        private final Iterable<Condicao.Real> condicoes;

        public CondicaoMultiplica(Iterable<Condicao.Real> condicoes) {
            this.condicoes = condicoes;
        }

        @Override
        public BigDecimal avaliar(Grade grade) {
            BigDecimal resposta = BigDecimal.ONE;
            for (Condicao.Real condicao : condicoes) {
                resposta = resposta.multiply(condicao.avaliar(grade));
            }
            return resposta;
        }
    }

    public static Condicao.Real multiplicar(Iterable<Condicao.Real> condicoes) {
        return new CondicaoMultiplica(condicoes);
    }

    public static Condicao.Real multiplicar(Condicao.Real... condicoes) {
        return multiplicar(Arrays.asList(condicoes));
    }

    private static final class CondicaoSoma implements Condicao.Real {

        private final Iterable<Condicao.Real> condicoes;

        public CondicaoSoma(Iterable<Condicao.Real> condicoes) {
            this.condicoes = condicoes;
        }

        @Override
        public BigDecimal avaliar(Grade grade) {
            BigDecimal resposta = BigDecimal.ONE;
            for (Condicao.Real condicao : condicoes) {
                resposta = resposta.add(condicao.avaliar(grade));
            }
            return resposta;
        }
    }

    public static Condicao.Real somar(Iterable<Condicao.Real> condicoes) {
        return new CondicaoSoma(condicoes);
    }

    public static Condicao.Real somar(Condicao.Real... condicoes) {
        return somar(Arrays.asList(condicoes));
    }

    private static final class CondicaoMenos implements Condicao.Real {

        private final Condicao.Real condicao;

        public CondicaoMenos(Condicao.Real condicao) {
            this.condicao = condicao;
        }

        @Override
        public BigDecimal avaliar(Grade grade) {
            return condicao.avaliar(grade).negate();
        }
    }

    public static Condicao.Real negar(Condicao.Real condicao) {
        return new CondicaoMenos(condicao);
    }

    private static final class ConstanteReal implements Condicao.Real {

        private final BigDecimal constante;

        public ConstanteReal(BigDecimal constante) {
            this.constante = constante;
        }

        @Override
        public BigDecimal avaliar(Grade grade) {
            return constante;
        }
    }

    public static Condicao.Real valor(BigDecimal constante) {
        return new ConstanteReal(constante);
    }
}