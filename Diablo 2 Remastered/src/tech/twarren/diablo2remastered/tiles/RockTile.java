package tech.twarren.diablo2remastered.tiles;

import tech.twarren.diablo2remastered.gfx.Assets;

public class RockTile extends Tile {

	public RockTile(int id) {
		super(Assets.dirt, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
}
