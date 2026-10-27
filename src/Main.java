public class Main {

    public static int funcaoHash(int chave) {
        return chave % 100;
    }

    public static void main(String[] args) {
        No[] tabela = new No[100];

        for (int i = 0; i < 100; i++) {
            tabela[i] = new No(-1);
        }

        java.util.Random random = new java.util.Random(1090);

        int ultimo1 = -1;
        int ultimo2 = -1;
        int ultimo3 = -1;

        for (int i = 0; i < 1000; i++) {
            int numero = random.nextInt(100001);
            int posicao = funcaoHash(numero);

            if (tabela[posicao].chave == -1) {
                tabela[posicao].chave = numero;
            } else {
                ultimo1 = ultimo2;
                ultimo2 = ultimo3;
                ultimo3 = numero;

                No novo = new No(numero);
                No atual = tabela[posicao];

                if (numero < atual.chave) {
                    novo.chave = atual.chave;
                    novo.proximo = atual.proximo;
                    atual.chave = numero;
                    atual.proximo = novo;
                } else {
                    while (atual.proximo != null && atual.proximo.chave < numero) {
                        atual = atual.proximo;
                    }
                    novo.proximo = atual.proximo;
                    atual.proximo = novo;
                }
            }
        }

        int posicoesVazias = 0;
        int listasGeradas = 0;
        int maiorTamanho = 0;
        int posicaoMaior = -1;

        for (int i = 0; i < 100; i++) {
            if (tabela[i].chave == -1) {
                posicoesVazias++;
            } else {
                int tamanho = 1;
                No atual = tabela[i].proximo;

                while (atual != null) {
                    tamanho++;
                    atual = atual.proximo;
                }

                if (tabela[i].proximo != null) {
                    listasGeradas++;
                }

                if (tamanho > maiorTamanho) {
                    maiorTamanho = tamanho;
                    posicaoMaior = i;
                }
            }
        }

        System.out.println("Posicao da maior lista: " + posicaoMaior);
        System.out.println("Tamanho da maior lista (com o no da tabela): " + maiorTamanho);
        System.out.print("Maior lista: ");

        No atual = tabela[posicaoMaior];
        while (atual != null) {
            System.out.print(atual.chave);
            if (atual.proximo != null) {
                System.out.print(" -> ");
            }
            atual = atual.proximo;
        }
        System.out.println();

        System.out.println("Listas encadeadas geradas: " + listasGeradas);
        System.out.println("Posicoes vazias: " + posicoesVazias);
        System.out.println("Tres ultimos valores inseridos em listas: " + ultimo1 + ", " + ultimo2 + ", " + ultimo3);
    }
}