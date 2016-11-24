/**
 * Copyright (c) 2016 Mark Rienstra
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package game.commands;

import game.Game;
import game.entities.Player;

public final class ScoreCommand extends Command
{
    protected ScoreCommand()
    {
        super ("score");
    }
    
    protected void parse(String command)
    {
        String strArray[] = command.split(" ");
        if (strArray.length < 3)
        {
            System.err.println("The cheat entered is too small!");
        }
        else
        {
            Player player = Game.getGame().getPlayer();
            int amount = Integer.parseInt(strArray[2]);
            if (strArray[1].trim().equalsIgnoreCase("set"))
            {
                player.setScore(amount);
            }
            else if (strArray[1].trim().equalsIgnoreCase("add"))
            {
                player.addScore(amount);
            }
            else if (strArray[1].trim().equalsIgnoreCase("subtract"))
            {
                player.removeScore(amount);
            }
        }
    }
}
