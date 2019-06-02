package test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class SCMTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(dao.SCM.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void setConnection() {
    }

    @Test
    public void selectSettleCategory() {
    }

    @Test
    public void addSettleCategory() {
    }

    @Test
    public void updateSettleCategory() {
    }

    @Test
    public void updateSettleCategorySave() {
    }

    @Test
    public void deleteSettleCategory() {
    }
}
