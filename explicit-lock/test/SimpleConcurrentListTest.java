import currentList.ConcurrentList;
import threads.B;
import threads.I;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import threads.R;


class SimpleConcurrentListTest {

    @Test
    void inserirVarios() {
        ConcurrentList lista = new ConcurrentList();
        int NUM_THREADS = 10;

        // cria as threads de inserção
        I[] inserir = new I[NUM_THREADS];
        for(int i = 0; i < NUM_THREADS; i++) {
            inserir[i] = new I("Inserir " + (i+1), lista, i);
        }

        // roda as threads
        for(int i = 0; i < NUM_THREADS; i++) {
            inserir[i].start();
        }

        // espera todas threads terminarem
        try {
            for(int i = 0; i < NUM_THREADS; i++) {
                inserir[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // pega o somatório dos valores da lista
        int sum = lista.sum();
        System.out.println("Somatório: " + sum);

        // verifica se o somatório está correto
        Assertions.assertEquals(45, sum);
    }

    @Test
    void removerVarios() {
        // cria uma lista de [0, ..., 9]
        ConcurrentList lista = new ConcurrentList();
        for(int i = 0; i < 10; i++) {
            lista.innerList.add(i);
        }
        // verifica se o somatório inicial está correto
        Assertions.assertEquals(45, lista.sum());

        // cria as threads de remoção
        int NUM_THREADS = 20;
        R[] remover = new R[NUM_THREADS];
        for(int i = 0; i < NUM_THREADS; i++) {
            remover[i] = new R("Remover " + (i+1), lista, i);
        }

        // executa as threads
        for(int i = 0; i < NUM_THREADS; i++) {
            remover[i].start();
        }

        // espera todas threads terminarem
        try {
            for(int i = 0; i < NUM_THREADS; i++) {
                remover[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // pega o somatório dos valores da lista
        int sum = lista.sum();
        System.out.println("Somatório: " + sum);

        // verifica se o somatório está correto
        Assertions.assertEquals(0, sum);
    }

    @Test
    void buscarVarios() {
        // cria uma lista de [0, ..., 9]
        ConcurrentList lista = new ConcurrentList();
        for(int i = 0; i < 10; i++) {
            lista.innerList.add(i);
        }

        // cria as threads de busca
        int NUM_THREADS = 20;
        B[] buscar = new B[NUM_THREADS];
        for(int i = 0; i < NUM_THREADS; i++) {
            buscar[i] = new B("Buscar " + (i+1), lista, i);
        }

        // executa as threads
        for(int i = 0; i < NUM_THREADS; i++) {
            buscar[i].start();
        }

        // verifica se PODE ativar o lock de read
        Assertions.assertEquals(true, lista.lock.readLock().tryLock());

        // espera todas threads terminarem
        try {
            for(int i = 0; i < NUM_THREADS; i++) {
                buscar[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    void buscarRemoverInserirVarios() {
        // cria uma lista de [0, ..., 9]
        ConcurrentList lista = new ConcurrentList();
        for(int i = 0; i < 10; i++) {
            lista.innerList.add(i);
        }
        // verifica se o somatório inicial está correto
        Assertions.assertEquals(45, lista.sum());

        // cria as threads
        int NUM_THREADS = 10;
        R[] remover = new R[NUM_THREADS];
        B[] buscar = new B[NUM_THREADS];
        I[] inserir = new I[NUM_THREADS];
        for(int i = 0; i < NUM_THREADS; i++) {
            remover[i] = new R("Remover " + (i+1), lista, i);
            buscar[i] = new B("Buscar " + (i+1), lista, i);
            inserir[i] = new I("Inserir " + (i+1), lista, i);
        }

        // executa as threads
        for(int i = 0; i < NUM_THREADS; i++) {
            remover[i].start();
            buscar[i].start();
            inserir[i].start();
        }

        // espera todas threads terminarem
        try {
            for(int i = 0; i < NUM_THREADS; i++) {
                remover[i].join();
                buscar[i].join();
                inserir[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // pega o somatório dos valores da lista
        int sum = lista.sum();
        System.out.println("Somatório: " + sum);

        // verifica se o somatório está correto
        Assertions.assertEquals(45, sum);
    }
}