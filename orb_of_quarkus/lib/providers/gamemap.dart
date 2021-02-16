import 'package:flutter/foundation.dart';

import 'dungeon.dart';
import 'player.dart';

class GameMap {
  final int id;
  final String story;
  final List<Dungeon> dungeons;
  final Player player;

  GameMap({
    @required this.id,
    @required this.story,
    @required this.dungeons,
    @required this.player,
  });
}
