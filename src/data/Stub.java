package data;

import model.ButterflyManager;

public class Stub implements Loadable<ButterflyManager> {

    @Override
    public ButterflyManager load() {
        ButterflyManager result = new ButterflyManager();

        result.addButterfly("Jerry");
        result.getButterflies().get(0).addColor(50, 100, 150);
        result.getButterflies().get(0).addColor(150, 0, 150);
        result.getButterflies().get(0).addColor(0, 100, 0);

        result.addButterfly("Tony");
        result.getButterflies().get(1).addColor(50, 100, 150);
        result.getButterflies().get(1).addColor(20, 0, 240);

        return result;
    }
}
