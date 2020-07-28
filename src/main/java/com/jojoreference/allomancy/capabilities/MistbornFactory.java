package com.jojoreference.allomancy.capabilities;

import java.util.concurrent.Callable;

public class MistbornFactory implements Callable<IMistborn> {
    @Override
    public IMistborn call() throws Exception {
        return new Mistborn();
    }
}
