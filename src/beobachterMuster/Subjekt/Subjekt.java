package beobachterMuster.Subjekt;

import beobachter.Beobachter;

public interface Subjekt {
    void meldeAn(Beobachter beobachter);
    void meldeAb(Beobachter beobachter);
    void benachrichtige();
}
