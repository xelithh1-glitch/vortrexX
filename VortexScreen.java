package dev.vortex.client;

import java.util.List;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/** Native Minecraft ClickGUI reconstructed from the original Vortex React UI. */
public final class VortexScreen extends Screen {
    private static final int BG = 0xF0101018;
    private static final int PANEL = 0xF51B1926;
    private static final int MUTED = 0xFF9B91AC;
    private static final int WHITE = 0xFFF2ECFA;
    private static final String[] CATEGORIES = {"all", "combat", "movement", "render", "player", "world"};
    private String category = "combat";
    private int page = 0;
    private int left, top, paneWidth, paneHeight;
    public VortexScreen() { super(Component.literal("Vortex Client")); }

    @Override protected void init() {
        int w = Math.min(570, width - 20);
        int h = Math.min(344, height - 20);
        left = (width-w)/2; top = (height-h)/2; paneWidth=w; paneHeight=h;
        int sidebar = 115;
        int by = top+51;
        for (String cat : CATEGORIES) {
            final String selected = cat;
            var button = Button.builder(Component.literal((cat.equals(category)?"» ":"  ")+title(cat)),
                 b -> { category=selected;page=0;rebuildWidgets(); })
                 .bounds(left+9, by, sidebar-18, 22).build();
            addRenderableWidget(button); by+=25;
        }
        var list = ModuleStore.category(category);
        int cellWidth = (w-sidebar-30)/2;
        int rowHeight = 39;
        int rows = Math.max(1,(h-114)/rowHeight);
        int perPage=rows*2;
        int pages=Math.max(1,(list.size()+perPage-1)/perPage);
        page=Math.min(page,pages-1);
        for(int i=page*perPage;i<Math.min(list.size(), (page+1)*perPage);i++) {
            var mod=list.get(i);
            int index=i-page*perPage;
            int x=left+sidebar+11+(index%2)*(cellWidth+7);
            int y=top+51+(index/2)*rowHeight;
            addRenderableWidget(Button.builder(Component.literal((mod.enabled?"[ON] ":"[OFF] ")+mod.name),
              b -> {ModuleStore.toggle(mod);rebuildWidgets();})
              .bounds(x,y,cellWidth,32).build());
        }
        addRenderableWidget(Button.builder(Component.literal("<"), b->{page=Math.max(0,page-1);rebuildWidgets();})
           .bounds(left+sidebar+11,top+h-34,22,20).build());
        addRenderableWidget(Button.builder(Component.literal(">"), b->{page=Math.min(pages-1,page+1);rebuildWidgets();})
           .bounds(left+sidebar+87,top+h-34,22,20).build());
        addRenderableWidget(Button.builder(Component.literal(VortexClient.hudVisible?"HUD: ON":"HUD: OFF"),
           b->{VortexClient.hudVisible=!VortexClient.hudVisible; rebuildWidgets();})
           .bounds(left+w-96,top+12,85,20).build());
    }
    private static String title(String cat) {return cat.equals("all")?"ALL MODULES":cat.toUpperCase();}
    @Override public void render(GuiGraphics g,int mouseX,int mouseY,float delta) {
        g.fill(0,0,width,height,0x9904000A);
        g.fill(left,top,left+paneWidth,top+paneHeight,BG);
        g.fill(left,top,left+paneWidth,top+3,VortexClient.PURPLE);
        g.fill(left,top+40,left+paneWidth,top+41,0xFF363040);
        g.fill(left+114,top+41,left+115,top+paneHeight,0xFF363040);
        g.drawString(font,"VORTEX",left+12,top+12,WHITE,true);
        g.drawString(font,"CLIENT 1.21.11",left+12,top+25,MUTED,false);
        g.drawString(font,"CATEGORY: "+category.toUpperCase(),left+127,top+19,VortexClient.PURPLE,true);
        g.drawString(font,"Page "+(page+1)+"   |   "+ModuleStore.enabled()+" / "+ModuleStore.ALL.size()+" toggled",
          left+125,top+paneHeight-30,MUTED,false);
        g.drawString(font,"UI DEMO - no gameplay effects",left+125,top+paneHeight-14,MUTED,false);
        super.render(g,mouseX,mouseY,delta);
    }
    @Override public boolean isPauseScreen() {return false;}
}
