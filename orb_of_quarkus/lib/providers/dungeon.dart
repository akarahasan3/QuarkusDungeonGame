import 'package:flutter/foundation.dart';

import 'gamemap.dart';
import 'monster.dart';
import 'weapon.dart';

class Dungeon {
  final int id;
  final int healingPotion;
  final GameMap map;
  final Monster monster;
  final Weapon weapon;

  Dungeon({
    @required this.id,
    @required this.healingPotion,
    @required this.map,
    @required this.monster,
    @required this.weapon,
  });
}
