package techreborn.tiles.lesu;

import java.util.ArrayList;

public class LESUNetwork {

    public ArrayList<TileLESUStorage> storages = new ArrayList<>();

    public TileLESU master;

    public void addElement(TileLESUStorage lesuStorage) {
        if (!storages.contains(lesuStorage) && storages.size() < 5000) {
            storages.add(lesuStorage);
        }
    }

    public void removeElement(TileLESUStorage lesuStorage) {
        storages.remove(lesuStorage);
        rebuild();
    }

    private void rebuild() {
        master = null;
        for (TileLESUStorage lesuStorage : storages) {
            lesuStorage.findAndJoinNetwork(lesuStorage.getWorld(), lesuStorage.getPos().getX(),
                    lesuStorage.getPos().getY(), lesuStorage.getPos().getZ());
        }
    }

    public void merge(LESUNetwork network) {
        if (network != this) {
            ArrayList<TileLESUStorage> tileLesuStorages = new ArrayList<>();
            tileLesuStorages.addAll(network.storages);
            network.clear(false);
            for (TileLESUStorage lesuStorage : tileLesuStorages) {
                lesuStorage.setNetwork(this);
            }
            if (network.master != null && this.master == null) {
                this.master = network.master;
            }
        }
    }

    private void clear(boolean clearTiles) {
        if (clearTiles) {
            for (TileLESUStorage tileLesuStorage : storages) {
                tileLesuStorage.resetNetwork();
            }
        }
        storages.clear();
    }

}
